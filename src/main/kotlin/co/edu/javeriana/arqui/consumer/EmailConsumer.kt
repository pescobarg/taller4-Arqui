package co.edu.javeriana.arqui.consumer

import co.edu.javeriana.arqui.service.EmailService
import io.smallrye.reactive.messaging.annotations.Blocking
import org.eclipse.microprofile.reactive.messaging.Incoming
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class EmailConsumer {

    @Inject
    lateinit var emailService: EmailService

    @Incoming("emails")
    @Blocking
    fun recibirMensaje(mensaje: String) {
        println("Mensaje recibido por rabbit")
        emailService.enviarCorreo(mensaje)
    }
}
