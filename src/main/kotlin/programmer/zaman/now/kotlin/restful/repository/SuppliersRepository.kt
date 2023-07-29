package programmer.zaman.now.kotlin.restful.repository

import org.springframework.data.jpa.repository.JpaRepository
import programmer.zaman.now.kotlin.restful.entity.Suppliers

interface SuppliersRepository : JpaRepository<Suppliers, Int>{
}