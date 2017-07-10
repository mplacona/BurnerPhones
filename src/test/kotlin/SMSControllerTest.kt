import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.containsString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import uk.co.placona.BurnerPhones.SMSController


@RunWith(SpringRunner::class)
class SMSControllerTest {
    lateinit var mockMvc: MockMvc

    @InjectMocks
    lateinit var controller: SMSController

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        System.setProperty("MY_NUMBER", "+44 1234567890")
    }

    @Test
    @Throws(Exception::class)
    fun contextLoads(){
        assertThat(controller).isNotNull();
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnDefaultMessage() {
        this.mockMvc.perform(get("/sms/")).andDo(print()).andExpect(status().isOk)
                .andExpect(content().string(containsString("A nice looking sms controller")))
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnForwardSMSTwiML() {
        val toNumber = System.getProperty("MY_NUMBER")
        val expectedResult = "<Response><Message to=\"$toNumber\"><Body>Message from: +1234567890 \n This is a test message</Body></Message></Response>"
        mockMvc.perform(
                post("/sms/forwardSMS")
                    .param("From", "+1234567890")
                    .param("Body", "This is a test message"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString(expectedResult)))
    }


}