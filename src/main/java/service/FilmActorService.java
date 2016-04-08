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

import facade.FilmActorFacade;
import model.FilmActor;

@Path("/filmsActors")
public class FilmActorService {
	
	@EJB 
	FilmActorFacade filmActorFacadeEJB;
	
	Logger logger = Logger.getLogger(FilmActorService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<FilmActor> findAll(){
		return filmActorFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<FilmActor> findList(@PathParam("id") Integer id) {
        return filmActorFacadeEJB.findList(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(FilmActor entity) {
        filmActorFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, FilmActor entity) {
    	entity.setFilmId(id.intValue());
        filmActorFacadeEJB.edit(entity);
    }
    
    @DELETE
    @Path("actor_{id}/film_{id2}")
    @Consumes({"application/xml", "application/json"})
    public void remove(@PathParam("id") Integer id, @PathParam("id2") Integer id2, FilmActor entity){
    	boolean a = false;
    	try{
    		entity = filmActorFacadeEJB.findActorFilm(id,id2).get(0);
        	
    	}catch(Exception e){
    		a = true;
    	}
    	 	if(!a)
    	 		filmActorFacadeEJB.remove(entity);
    }

	

}
