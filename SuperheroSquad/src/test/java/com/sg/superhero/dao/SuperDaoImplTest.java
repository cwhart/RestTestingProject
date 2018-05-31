package com.sg.superhero.dao;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.LocationDao;
import com.sg.superhero.dao.interfaces.SuperDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Super;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SuperDaoImplTest {

    @Inject
    SuperDao superDao;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Super superPerson = testHelper.createTestSuper();
        superDao.create(superPerson);

        //Act
        Super createdSuper = superDao.create(superPerson);


        //Assert
        assert (createdSuper.getId() != null);
        assertSuperEquals(createdSuper, superPerson);
    }

    @Test
    public void retrieve() {

        //Arrange
        Super superPerson = testHelper.createTestSuper();
        superDao.create(superPerson);

        //Act
        Super readSuper = superDao.retrieve(superPerson.getId());

        //Assert
        assert (readSuper.getId() != null);
        assertSuperEquals(superPerson, readSuper);
    }

    @Test
    public void update() {

        //Arrange
        Super superPerson = testHelper.createTestSuper();
        superDao.create(superPerson);

        //Change some stuff
        superPerson.setName("Supergirl");
        superPerson.setDescription("The Woman of Iron");

        //Act
        superDao.update(superPerson);

        //Assert
        Super readSuper = superDao.retrieve(superPerson.getId());
        assert "Supergirl".equals(readSuper.getName());
        assert "The Woman of Iron".equals(readSuper.getDescription());
    }

    @Test
    public void delete() {

        //Arrange
        Super superPerson = testHelper.createTestSuper();
        superDao.create(superPerson);

        //Act
        superDao.delete(superPerson);

        Super readSuper = superDao.retrieve(superPerson.getId());

        //Assert
        assert (null == readSuper);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleSupers(25);

        //Act
        List<Super> superList = superDao.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert superList.size() == 25;
    }

    @Test
    public void getSupersByOrganization() {
    }

    @Test
    public void getSupersByLocation() {
    }

    @Test
    public void getSupersBySighting() {
    }

    public void assertSuperEquals(Super super1, Super super2) {
        assert (super1.getName().equals(super2.getName()));
        assert (super1.getDescription().equals(super2.getDescription()));

    }
}