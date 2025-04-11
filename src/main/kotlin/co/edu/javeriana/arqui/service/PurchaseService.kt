package co.edu.javeriana.arqui.logic.service

import co.edu.javeriana.arqui.logic.entity.Purchase
import co.edu.javeriana.arqui.logic.repository.PurchaseRepository
import io.smallrye.reactive.messaging.annotations.Channel
import io.smallrye.reactive.messaging.annotations.Emitter
import io.smallrye.reactive.messaging.annotations.OnOverflow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class PurchaseService(
    private val repo: PurchaseRepository,
    @Channel("purchases-out") @OnOverflow(bufferSize = 100) private val emitter: Emitter<Purchase>
) {

    @Transactional
    suspend fun saveAndEmit(purchase: Purchase): Purchase {
        // Guarda síncrono en la BD
        val saved = repo.persist(purchase)
        // Envía al canal AMQP (RabbitMQ)
        emitter.send(purchase)
        return saved
    }

    suspend fun listAll(): List<Purchase> =
        repo.listAll().awaitFirst()
}
