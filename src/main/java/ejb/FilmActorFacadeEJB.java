package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.FilmActorFacade;
import model.FilmActor;

@Stateless
public class FilmActorFacadeEJB extends AbstractFacade<FilmActor> implements FilmActorFacade {
	
	
	@PersistenceContext(unitName = "sakilaPU")
	private EntityManager em;
	
	public FilmActorFacadeEJB() {
		super(FilmActor.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	public List<FilmActor> findList(int id) {
		return em.createQuery(
		    "SELECT c FROM FilmActor c WHERE c.actorId = :id_algo")
				.setParameter("id_algo", id)
				.getResultList();
		}
	
	public List<FilmActor> findActorFilm(int id, int id2){
		return em.createQuery(
				"SELECT a FROM FilmActor a WHERE a.actorId = :id_1 AND a.filmId = :id_2")
				.setParameter("id_1",id)
				.setParameter("id_2",id2)
				.getResultList();
		
	}
}
