package co.edu.javeriana.arqui.logic.controller

import co.edu.javeriana.arqui.logic.entity.Purchase
import co.edu.javeriana.arqui.logic.service.PurchaseService
import io.smallrye.common.annotation.Blocking
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/purchases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PurchaseController(private val service: PurchaseService) {

    @POST
    fun create(purchase: Purchase): Response = runBlocking {
        val saved = service.saveAndEmit(purchase)
        Response.status(Response.Status.CREATED).entity(saved).build()
    }

    @GET
    @Blocking
    fun list(): List<Purchase> =
        runBlocking { service.listAll() }
}
