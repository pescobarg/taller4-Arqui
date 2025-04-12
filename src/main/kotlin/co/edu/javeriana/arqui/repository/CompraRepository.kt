package co.edu.javeriana.arqui.repository

import co.edu.javeriana.arqui.entity.Compra
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CompraRepository : PanacheRepository<Compra>
