import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableHystrix
@RefreshScope
public class ConsumerController {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/api/demo/get")
    @HystrixCommand(fallbackMethod = "hiFallback")
    public String consumer(){
        return this.restTemplate.getForObject("http://localhost:8771/producer", String.class);
    }

}