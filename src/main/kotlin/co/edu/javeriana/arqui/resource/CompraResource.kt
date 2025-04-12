package co.edu.javeriana.arqui.resource

import co.edu.javeriana.arqui.entity.Compra
import co.edu.javeriana.arqui.service.CompraService
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/compras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CompraResource {

    @Inject
    lateinit var compraService: CompraService

    @POST
    fun crearCompra( compra: Compra): Response {
        println("Compra registrada")
        val creada = compraService.registrarCompra(compra)
        return Response.ok(creada).build()
    }
}
