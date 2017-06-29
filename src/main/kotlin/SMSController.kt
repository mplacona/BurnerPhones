package uk.co.placona.BurnerPhones

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController class SMSController {
    @RequestMapping(value = "/")
    fun helloSpringBoot() = "Hello Spring Boot"
}