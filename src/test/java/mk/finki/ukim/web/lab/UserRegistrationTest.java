package mk.finki.ukim.web.lab;

import mk.finki.ukim.web.lab.model.User;
import mk.finki.ukim.web.lab.model.enumerations.Role;
import mk.finki.ukim.web.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.web.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.web.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.web.lab.repository.jpa.UserRepositoryJPA;
import mk.finki.ukim.web.lab.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepositoryJPA userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
//        LocalDate localDate = LocalDate.parse("2001-06-08");
        User user = new User("username", "password", "name", "surename",LocalDate.now(), Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void TestSuccessRegister(){
        User user = this.service.register("username", "password", "password", "name", "surname",LocalDate.now(), Role.ROLE_USER);

        Mockito.verify(this.service).register("username", "password", "password", "name", "surname",LocalDate.now(), Role.ROLE_USER);

        Assert.assertNotNull("User is null", user);

        Assert.assertEquals("name do not match", "name", user.getUserFullname().getName());
        Assert.assertEquals("password do not mach", "password", user.getPassword());
        Assert.assertEquals("username do not mach", "username", user.getUsername());
        Assert.assertEquals("surename do not mach", "surename", user.getUserFullname().getSurname());
        Assert.assertEquals("role do not mach", Role.ROLE_USER, user.getRole());
    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(null, "password", "password", "name", "surename", LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(null, "password", "password", "name", "surename", LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, "password", "password", "name", "surename", LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", "name", "surename", LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password, "password", "name", "surename",LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password", "name", "surename",LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password, "password", "name", "surename",LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password", "name", "surename",LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username, password, confirmPassword, "name", "surename",LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, confirmPassword, "name", "surename",LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testDuplicateUsername() {
        User user = new User("username", "password", "name", "surename",LocalDate.now(), Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "password", "password", "name", "surename",LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", "name", "surename",LocalDate.now(), Role.ROLE_USER);
    }
}
