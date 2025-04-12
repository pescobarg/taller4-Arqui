package co.edu.javeriana.arqui.service

import io.quarkus.mailer.Mail
import io.quarkus.mailer.Mailer
import io.smallrye.common.annotation.Blocking
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Incoming

@ApplicationScoped
class EmailService {

    @Inject
    lateinit var mailer: Mailer

    @Incoming("emails-in")
    fun enviarCorreo(mensaje: String) {
        println("Mensaje recibido por RabbitMQ: $mensaje")
        
        val destinatario = "12pabloes@gmail.com"  
        val contenidoCorreo = mensaje  

        val mail = Mail().apply {
            from = "micorreo@gmail.com" // Dirección de correo del remitente
            to = listOf(destinatario) // Dirección de correo del destinatario
            subject = "Confirmación de compra" // Asunto del correo
            text = contenidoCorreo // Cuerpo del correo
        }

        try {
            mailer.send(mail) // Enviar el correo
            println("✅ Correo enviado a $destinatario con mensaje: $contenidoCorreo")
        } catch (e: Exception) {
            println("❌ Error enviando correo: ${e.message}")
            e.printStackTrace() // Mostrar detalles del error si falla
        }
    }
}
