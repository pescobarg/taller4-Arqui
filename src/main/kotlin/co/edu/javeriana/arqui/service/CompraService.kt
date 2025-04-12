package co.edu.javeriana.arqui.service

import co.edu.javeriana.arqui.entity.Compra
import co.edu.javeriana.arqui.repository.CompraRepository
import io.smallrye.reactive.messaging.MutinyEmitter
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.reactive.messaging.Channel
import jakarta.transaction.Transactional
import io.smallrye.reactive.messaging.annotations.Broadcast



@ApplicationScoped
class CompraService {

    @Inject
    lateinit var compraRepository: CompraRepository

    @Inject
    @Channel("emails")
    @Broadcast
    lateinit var emitter: MutinyEmitter<String>

    @Transactional
    fun registrarCompra(compra: Compra): Compra {
        compraRepository.persist(compra)
        emitter.send("Compra realizada por ${compra.cliente} - Producto: ${compra.producto}")
            .subscribe().with(
                { println("✅ Mensaje emitido correctamente") },
                { err -> println("❌ Error al emitir mensaje: ${err.message}") }
            )
        println("Correo enviado")       
         return compra
    }
}
