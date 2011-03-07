import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.*

scan('5 minutes')
setupAppenders()

def setupAppenders() {

    appender("FILE", FileAppender) {
        file = "log/drools-shell.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%-4relative [%thread] %-5level %logger{35} - %msg%n"
        }
    }

    logger("org.springframework", INFO)
    root(DEBUG, ["FILE"])

}
