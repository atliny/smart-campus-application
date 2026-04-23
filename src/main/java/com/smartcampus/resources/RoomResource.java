/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import com.smartcampus.exceptions.RoomNotEmptyException;
import com.smartcampus.storage.DataStore;
import com.smartcampus.models.Room;

/**
 *
 * @author anudhi
 */

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {
    private final DataStore store = DataStore.getInstance();

    @GET
    public Response getAllRooms() {
        Collection<Room> rooms = store.getRooms().values();
        return Response.ok(rooms).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room) {
        if (room == null || room.getId() == null || room.getId().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "RoomID is required"))
                    .build();
        }
        
        if (store.getRoom(room.getId()) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("error", "A room with ID '" + room.getId() + "' already exists."))
                    .build();
        }         
        
        store.putRoom(room);
        
        URI location = UriBuilder.fromResource(RoomResource.class)
                .path(room.getId()).build();
        
        return Response.created(location).entity(room).build();
    }
    
    @GET
    @Path("/{roomId}")
    public Response getRoom(@PathParam("roomId") String roomId) {
        Room room = store.getRoom(roomId);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Room '" + roomId + "' not found."))
                    .build();
        }
        return Response.ok(room).build();
    }
    
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        Room room = store.getRoom(roomId);
                
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Room '" + roomId + "' not found."))
                    .build();
        }
        
        if (!room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException(roomId, room.getSensorIds());
        }

        store.deleteRoom(roomId);
        return Response.ok(Map.of("message", "Room " + roomId + " deleted successfully.")).build();
    }
}
