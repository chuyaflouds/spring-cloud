import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableHystrix
@RefreshScope
public class ProducerController {

    @RequestMapping("/producer")
    @HystrixCommand(fallbackMethod = "hiFallback")
    public String produce(){
        return "success";
    }
}