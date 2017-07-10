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

    @RequestMapping(value = "/forwardCall", produces = arrayOf("text/xml"))
    fun forwardCall(@RequestParam(value = "From") from: String): String{

        var call = Dial.Builder().number(Number.Builder(System.getProperty("MY_NUMBER")).build()).build()
        return VoiceResponse.Builder()
                .say(Say.Builder("You have a call from $from").voice(Say.Voice.ALICE).build())
                .dial(call).build().toXml()
    }
}