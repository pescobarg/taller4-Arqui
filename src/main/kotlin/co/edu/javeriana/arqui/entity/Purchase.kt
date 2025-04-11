package co.edu.javeriana.arqui.logic.entity

import io.quarkus.hibernate.reactive.panache.PanacheEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Column

@Entity
@Table(name = "purchases")
class Purchase : PanacheEntity() {

    @Column(nullable = false)
    lateinit var customerEmail: String

    @Column(nullable = false)
    var amount: Double = 0.0

    @Column(nullable = false)
    var createdAt: Long = System.currentTimeMillis()
}
