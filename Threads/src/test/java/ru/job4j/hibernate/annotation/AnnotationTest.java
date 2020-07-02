package ru.job4j.hibernate.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AnnotationTest {

    @Test
    public void whenCreateEngine() {
        try (
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                final Session session = sf.openSession();
        ) {
            session.beginTransaction();
            List<Engine> engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            assertTrue(engines.isEmpty());
            session.save(new Engine());
            session.save(new Engine());
            engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            assertThat(engines.size(), is(2));
            session.getTransaction().rollback();
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void whenDeleteEngine() {
        try (
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                final Session session = sf.openSession();
        ) {
            session.beginTransaction();
            List<Engine> engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            assertTrue(engines.isEmpty());
            session.save(new Engine());
            session.save(new Engine());
            engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            assertThat(engines.size(), is(2));
            session.delete(engines.get(0));
            engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            assertThat(engines.size(), is(1));
            session.getTransaction().rollback();
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void whenCreateDriver() {
        try (
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                final Session session = sf.openSession();
        ) {
            session.beginTransaction();
            List<Driver> drivers = session.createQuery("from ru.job4j.hibernate.annotation.Driver").list();
            assertTrue(drivers.isEmpty());
            HashSet<Car> cars = new HashSet<>();
            Car car = new Car();
            session.save(car);
            cars.add(car);
            session.save(new Driver());
            session.save(new Driver(cars));
            drivers = session.createQuery("from ru.job4j.hibernate.annotation.Driver").list();
            assertThat(drivers.size(), is(2));
            session.getTransaction().rollback();
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void whenDeleteDriver() {
        try (
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                final Session session = sf.openSession();
        ) {
            session.beginTransaction();
            List<Driver> drivers = session.createQuery("from ru.job4j.hibernate.annotation.Driver").list();
            assertTrue(drivers.isEmpty());
            session.save(new Driver());
            drivers = session.createQuery("from ru.job4j.hibernate.annotation.Driver").list();
            assertThat(drivers.size(), is(1));
            session.delete(drivers.get(0));
            drivers = session.createQuery("from ru.job4j.hibernate.annotation.Driver").list();
            assertTrue(drivers.isEmpty());
            session.getTransaction().rollback();
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void whenUpdateCar() {
        try (
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                final Session session = sf.openSession();
        ) {
            session.beginTransaction();
            session.save(new Engine());
            List<Engine> engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            session.save(new Car());
            List<Car> cars = session.createQuery("from ru.job4j.hibernate.annotation.Car").list();
            assertThat(cars.size(), is(1));
            session.save(new Driver());
            List<Driver> drivers = session.createQuery("from ru.job4j.hibernate.annotation.Driver").list();
            Car car = cars.get(0);
            Driver driver = drivers.get(0);
            car.getDrivers().add(driver);
            car.setEngine(engines.get(0));
            session.update(car);
            Car carResult = session.get(Car.class, car.getId());
            assertThat(carResult.getDrivers(), is(car.getDrivers()));
            assertThat(carResult.getEngine(), is(engines.get(0)));
            session.getTransaction().rollback();
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void whenDeleteCar() {
        try (
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                final Session session = sf.openSession();
        ) {
            session.beginTransaction();
            session.save(new Engine());
            List<Engine> engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            session.save(new Car());
            List<Car> cars = session.createQuery("from ru.job4j.hibernate.annotation.Car").list();
            assertThat(cars.size(), is(1));
            session.save(new Driver());
            List<Driver> drivers = session.createQuery("from ru.job4j.hibernate.annotation.Driver").list();
            Car car = cars.get(0);
            Driver driver = drivers.get(0);
            car.getDrivers().add(driver);
            car.setEngine(engines.get(0));
            session.save(car);
            session.delete(car);
            Car carResult = session.get(Car.class, car.getId());
            assertNull(carResult);
            session.getTransaction().rollback();
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void whenCreateCar() {
        try (
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                final Session session = sf.openSession();
        ) {
            session.beginTransaction();
            List<Engine> engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            List<Car> cars = session.createQuery("from ru.job4j.hibernate.annotation.Car").list();
            assertTrue(engines.isEmpty());
            assertTrue(cars.isEmpty());
            session.save(new Engine());
            session.save(new Engine());
            engines = session.createQuery("from ru.job4j.hibernate.annotation.Engine").list();
            assertThat(engines.size(), is(2));
            session.save(new Car(engines.get(0)));
            session.save(new Car(engines.get(0)));
            cars = session.createQuery("from ru.job4j.hibernate.annotation.Car").list();
            assertThat(cars.size(), is(2));
            session.getTransaction().rollback();
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}

