package edu.msg.ro.service;

import edu.msg.ro.exceptions.BusinessException;
import edu.msg.ro.exceptions.ExceptionCode;
import edu.msg.ro.persistence.user.dao.UserPersistenceManager;
import edu.msg.ro.persistence.user.entity.User;
import edu.msg.ro.transfer.UserDTO;
import edu.msg.ro.utils.Encryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ejb.EJB;
import javax.inject.Inject;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserPersistenceManagerBeanTest {



    @Mock
    UserPersistenceManager userPersistenceMock;

    @InjectMocks
    UserManagementBean userManagementMock;

    @Test
    public void generateUsername_expectedMarini() {
        String username = userManagementMock.generateUsername("Ion","Marin");
        assertTrue("Expected marini but found " + username, username.equals("marini"));
    }

    @Test
    public void generateUsername_expectedIonion() {
        String username = userManagementMock.generateUsername("Ion","Ion");
        assertTrue("Expected ionion but found " + username, username.equals("ionion"));
    }

    @Test
    public void generateUsername_expectedPetric() {
        String username = userManagementMock.generateUsername("Calin","Petrindean");
        assertTrue("Expected petric but found " + username, username.equals("petric"));
    }

    @Test
    public void generateUsername_expectedba0000() {
        String username = userManagementMock.generateUsername("a","b");
        assertTrue("Expected ba0000 but found " + username, username.equals("ba0000"));
    }

    @Test
    public void testCreateSuffix_expectedEmpty(){
        when(userPersistenceMock.findUsersNameStartingWith(any(String.class))).thenReturn(new ArrayList<>());
        String suffix = userManagementMock.createSuffix("dorel0");
        assertEquals("",suffix );
    }

    @Test
    public void testCreateSuffix_expected4(){
        when(userPersistenceMock.findUsersNameStartingWith(any(String.class))).thenReturn(new ArrayList<String>(){{add("dorel0");add("dorel01"); add("dorel02"); add("dorel03");}});
        String suffix = userManagementMock.createSuffix("dorel0");
        assertEquals( "4",suffix);
    }

    @Test
    public void testCreateSuffix_expected7(){
        when(userPersistenceMock.findUsersNameStartingWith(any(String.class))).thenReturn(new ArrayList<String>(){{add("dorel04");add("dorel06");}});
        String suffix = userManagementMock.createSuffix("dorel0");
        assertEquals("7",suffix );
    }

    @Test
    public void testCreateSuffix_expected1(){
        when(userPersistenceMock.findUsersNameStartingWith(any(String.class))).thenReturn(new ArrayList<String>(){{add("marini");}});
        String suffix = userManagementMock.createSuffix("marini");
        assertEquals( "1",suffix);
    }

    @Test
    public void testLogin_WrongUsername(){

        when(userPersistenceMock.getUserForUsername(any(String.class))).thenReturn(null);

        try {
            userManagementMock.login("a","s");
            fail("Shouldn't reach at this point!");
        } catch (BusinessException e) {
            assertEquals(ExceptionCode.USERNAME_NOT_VALID,e.getExceptionCode());
        }
    }

    @Test
    public void testLogin_Succes(){
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("salut");
        when(user.getPassword()).thenReturn(Encryptor.encrypt("secret"));
        when(userPersistenceMock.getUserForUsername(any(String.class))).thenReturn(user);

        try {

            UserDTO userDTO = userManagementMock.login("salut","secret");
            assertEquals(userDTO.getUsername(),user.getUsername());
        } catch (BusinessException e) {
            fail("Shouldn't reach at this point!");
        }
    }

    @Test
    public void testCreateUser_Success(){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Cristi");
        userDTO.setLastName("Borcea");
        userDTO.setEmail("dinamo@msggroup.com");
        userDTO.setPhoneNumber("0777234152");
        userDTO.setPassword("BereGratis");
        try {
           UserDTO createdUser = userManagementMock.createUser(userDTO);
           assertEquals(userDTO.getFirstName(), createdUser.getFirstName());
            assertEquals(userDTO.getLastName(), createdUser.getLastName());
            assertEquals(userDTO.getEmail(), createdUser.getEmail());
            assertEquals("borcec",createdUser.getUsername());
        } catch (BusinessException e) {
            fail("You SHALL NOT PASS!");
        }

    }

}