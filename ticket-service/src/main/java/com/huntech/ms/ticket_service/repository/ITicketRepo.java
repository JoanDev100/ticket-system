package com.huntech.ms.ticket_service.repository;

import com.huntech.ms.ticket_service.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketRepo extends MongoRepository<Ticket, String> {
    List<Ticket> findByCreatedById(Integer userId);
    List<Ticket> findByStatus(String status);
    List<Ticket> findByPriority(String priority);
    List<Ticket> findByCategory(String category);

    // Búsqueda por texto
    @Query("{ 'title': { '$regex': ?0, '$options': 'i' } }")
    List<Ticket> findByTitleContaining(String title);

    @Query("{ 'description': { '$regex': ?0, '$options': 'i' } }")
    List<Ticket> findByDescriptionContaining(String description);

    List<Ticket> findByAssignedToUsername(String username);
}
