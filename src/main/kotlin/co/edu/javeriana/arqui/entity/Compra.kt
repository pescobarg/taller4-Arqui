package co.edu.javeriana.arqui.entity

import jakarta.persistence.*

@Entity
@Table(name = "compras")
data class Compra(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var cliente: String = "",

    @Column(nullable = false)
    var producto: String = ""
)
