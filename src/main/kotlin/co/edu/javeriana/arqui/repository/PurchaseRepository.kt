package co.edu.javeriana.arqui.logic.repository

import co.edu.javeriana.arqui.logic.entity.Purchase
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PurchaseRepository : PanacheRepository<Purchase> {}
