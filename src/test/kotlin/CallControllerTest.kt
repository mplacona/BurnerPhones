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
import uk.co.placona.BurnerPhones.CallController


@RunWith(SpringRunner::class)
class CallControllerTest {
    lateinit var mockMvc: MockMvc

    @InjectMocks
    lateinit var controller: CallController

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
        this.mockMvc.perform(get("/call/")).andDo(print()).andExpect(status().isOk)
                .andExpect(content().string(containsString("A nice looking call controller")))
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnForwardCallTwiML() {
        val toNumber = System.getProperty("MY_NUMBER")
        val expectedResult = "<Response><Say voice=\"alice\">You have a call from +1234567890</Say><Dial><Number>$toNumber</Number></Dial></Response>"

        mockMvc.perform(
                post("/call/forwardCall")
                        .param("From", "+1234567890"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString(expectedResult)))
    }


}