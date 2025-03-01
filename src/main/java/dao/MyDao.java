package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Customer;
import dto.FoodItem;
import dto.Hotel;

public class MyDao {
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("dev");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	
	public void saveHotel(Hotel hotel) {
		transaction.begin();
		manager.persist(hotel);
		transaction.commit();
	}

	public List<Hotel> findHotelByEmail(String email) {
		return manager.createQuery("select x from Hotel x where email=?1").setParameter(1, email).getResultList();
	}

	public void saveFoodItem(FoodItem foodItem) {
		transaction.begin();
		manager.persist(foodItem);
		transaction.commit();	
	}

	public List<FoodItem> fetchFoodByHotel(int id) {
		return manager.createQuery("select x from FoodItem x where hotel_id=?1").setParameter(1, id).getResultList();
	}

	public FoodItem fetchFoodById(int id) {
		return manager.find(FoodItem.class, id);
	}

	public void updateFoodItem(FoodItem foodItem) {
		transaction.begin();
		manager.merge(foodItem);
		transaction.commit();
	}

	public void deleteFoodItem(FoodItem foodItem) {
		transaction.begin();
		manager.remove(foodItem);
		transaction.commit();
	}

	public List<Customer> findCustomerByEmail(String email) {
		return manager.createQuery("select x from Customer x where email=?1").setParameter(1, email).getResultList();
	}

	public void saveCustomer(Customer customer) {
		transaction.begin();
		manager.persist(customer);
		transaction.commit();
		
	}

}
