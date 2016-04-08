package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.ActorFacade;
import facade.FilmActorFacade;
import model.Actor;
import model.Film;
import model.FilmActor;

@Path("/actors")
public class ActorService {
	
	@EJB 
	ActorFacade actorFacadeEJB;
	
	FilmActorFacade filmActorFacadeEJB;
	
	Logger logger = Logger.getLogger(ActorService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Actor> findAll(){
		return actorFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Actor find(@PathParam("id") Integer id) {
        return actorFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Actor entity) {
        actorFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Actor entity) {
    	entity.setActorId(id.intValue());
        actorFacadeEJB.edit(entity);
    }
	
    @DELETE
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void remove(@PathParam("id") Integer id, Actor entity){
    	List<FilmActor> l = filmActorFacadeEJB.findList(id);
    	for(int i = 0; i < l.size() ; i++){
    		filmActorFacadeEJB.remove(l.get(i));
    	}
    	entity = actorFacadeEJB.find(id);
    	actorFacadeEJB.remove(entity);
    }
    
    

}
