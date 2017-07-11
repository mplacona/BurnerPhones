package uk.co.placona.BurnerPhones

import com.twilio.twiml.*
import com.twilio.twiml.Number
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/call")
@RestController class CallController {
    @RequestMapping(value = "/")
    fun helloSpringBoot() = "A nice looking call controller"
}