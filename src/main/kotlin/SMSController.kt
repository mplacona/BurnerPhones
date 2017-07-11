package uk.co.placona.BurnerPhones

import com.twilio.twiml.Body
import com.twilio.twiml.Message
import com.twilio.twiml.MessagingResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/sms")
@RestController class SMSController {
    @RequestMapping(value = "/")
    fun helloSpringBoot() = "A nice looking sms controller"
}