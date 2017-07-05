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

    @RequestMapping(value = "/forwardSMS", produces = arrayOf("text/xml"))
    fun forwardSMS(@RequestParam(value = "From") from: String, @RequestParam(value = "Body") body: String): String{
        val message = Message.Builder()
                .to(System.getenv("MY_NUMBER"))
                .body(Body("Message from: $from \n $body")).build()
        return MessagingResponse.Builder().message(message).build().toXml()
    }
}