package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ActorFacade;
import model.Actor;
import model.FilmActor;

@Stateless
public class ActorFacadeEJB extends AbstractFacade<Actor> implements ActorFacade {
	
	
	@PersistenceContext(unitName = "sakilaPU")
	private EntityManager em;
	
	public ActorFacadeEJB() {
		super(Actor.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	public List<FilmActor> findListActor(int id) {
		return em.createQuery(
		    "SELECT c FROM FilmActor c WHERE c.actorId = :id_algo")
				.setParameter("id_algo", id)
				.getResultList();
		}

}
