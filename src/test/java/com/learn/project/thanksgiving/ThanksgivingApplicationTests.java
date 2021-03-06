package com.learn.project.thanksgiving;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.project.thanksgiving.Entity.GameCharacter;
import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Entity.Registry;
import com.learn.project.thanksgiving.Entity.Room;
import com.learn.project.thanksgiving.Repository.CharacterRepository;
import com.learn.project.thanksgiving.Repository.GameRepository;
import com.learn.project.thanksgiving.Repository.RoomRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ThanksgivingApplicationTests {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	GameRepository gameRepo;

    @MockBean
    CharacterRepository charRepo;

    @MockBean
    RoomRepository roomRepo;

	List<Registry> games;

	@Before
	public void setup() {

		/*games = Arrays.asList(new Registry("Game", "{1,Sword}"),
				new Registry("Game1", "{2,Dagger}"),
				new Registry("Game2", "{3,Pistol}"));*/
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void test_addGame() throws Exception {
	    Item item =  new Item(1, "Sword");
		Registry registry = new Registry();
		when(gameRepo.save(registry))
				.thenReturn(registry);

		String json = mapper.writeValueAsString(item);
		System.out.println(json);
		mockMvc.perform(MockMvcRequestBuilders.post("/object/create/Game")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());

		verify(gameRepo, times(1)).save(isA(Registry.class));

	}

    @Test
    public void test_deleteGame() throws Exception {
        Registry registry = new Registry();
        registry.setClassName("Game");
        Item item = new Item();
        item.setName("Sword");
        item.setId(1);
        String json = mapper.writeValueAsString(item);
        registry.setItem(json);
        when(gameRepo.findByClassName("Game")).thenReturn(registry);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/object/delete/Game/1"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(gameRepo, times(1)).findByClassName("Game");
        verify(gameRepo, times(1)).delete(registry);
        verifyNoMoreInteractions(gameRepo);

    }

    @Test
    public void test_invalidDeleteGame() throws Exception {

        Registry registry = new Registry();
        registry.setClassName("Game1");
        Item item = new Item();
        item.setName("Sword");
        item.setId(1);
        String json = mapper.writeValueAsString(item);
        registry.setItem(json);
        when(gameRepo.findByClassName("Game")).thenReturn(registry);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/object/delete/Game/3"))
                .andExpect(status().isNotFound())
                .andDo(print());

        verify(gameRepo, times(1)).findByClassName("Game");
        verify(gameRepo, times(0)).delete(registry);
        verifyNoMoreInteractions(gameRepo);

    }

    @Test

    public void test_getGame() throws Exception {
        Registry registry = new Registry();
        registry.setClassName("Game1");
        Item item = new Item();
        item.setName("Sword");
        item.setId(1);
        String json = mapper.writeValueAsString(item);
        registry.setItem(json);
	    when(gameRepo.findByClassName("Game1"))
                .thenReturn(registry);
        mockMvc.perform(MockMvcRequestBuilders.get("/object/get/Game1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.className", instanceOf(String.class)))
                .andExpect(jsonPath("$.*", hasSize(2)));

        verify(gameRepo, times(1)).findByClassName("Game1");
        verifyNoMoreInteractions(gameRepo);
    }


    @Test
    public void test_getAllGamesByClass() throws Exception{
        Registry registry = new Registry();
        registry.setClassName("Game1");
        Item item = new Item();
        item.setName("Sword");
        item.setId(1);
        String json = mapper.writeValueAsString(item);
        registry.setItem(json);
	    when(gameRepo.findByClassName("Game1")).thenReturn(registry);
        mockMvc.perform(MockMvcRequestBuilders.get("/object/get/Game1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
                //.andExpect(jsonPath("$", hasSize(3)));

        verify(gameRepo, times(1)).findByClassName("Game1");
        verifyNoMoreInteractions(gameRepo);

    }

    @Test
    public void test_getAllGames() throws Exception{
        List<Registry> regList = new ArrayList<>();
        Registry registry = new Registry();
        registry.setClassName("Game1");
        Item item = new Item();
        item.setName("Sword");
        item.setId(1);
        String json = mapper.writeValueAsString(item);
        registry.setItem(json);
        regList.add(registry);
	    when(gameRepo.findAll()).thenReturn(regList);
       mockMvc.perform(MockMvcRequestBuilders.get("/object/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
                //.andExpect(jsonPath("$", hasSize(3)));

        verify(gameRepo, times(1)).findAll();
        verifyNoMoreInteractions(gameRepo);

    }

    @Test
    public void test_getCharacters() throws Exception{
        GameCharacter charchar = new GameCharacter();
        Item item = new Item();
        String charjson = mapper.writeValueAsString(charchar);
        String json = mapper.writeValueAsString(item);
        Registry reg = new Registry();
        reg.setItem(json);
        reg.setGameCharacter(charjson);
        when(gameRepo.save(reg))
                .thenReturn(reg);
        mockMvc.perform(MockMvcRequestBuilders.post("/object/create/Game")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(gameRepo, times(1)).save(isA(Registry.class));

    }

    @Test
    public void test_moveRoom() throws Exception{
        GameCharacter charchar = new GameCharacter();
        charchar.setId(1L);
        charchar.setLocation(5);
        Room room  = new Room();
        room.setId(5L);
        Integer[] arr = new Integer[3];
        arr[0]=1;
        arr[1]=2;
        arr[2] = 3;
        room.setExits(arr);
        when(charRepo.findById(1L))
                .thenReturn(Optional.of(charchar));
        when(roomRepo.findById(5L)).thenReturn(Optional.of(room));
        String json = mapper.writeValueAsString(charchar);
        mockMvc.perform(MockMvcRequestBuilders.post("/move/1/to/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(charRepo, times(1)).save(isA(GameCharacter.class));

    }

    @Test
    public void test_addInventory() throws Exception{
        GameCharacter charchar = new GameCharacter();
        Registry registry = new Registry();
        registry.setClassName("Game");
        charchar.setId(1L);
        charchar.setLocation(5);
        Room room  = new Room();
        room.setId(5L);
        Integer[] arr = new Integer[3];
        arr[0]=1;
        arr[1]=2;
        arr[2] = 3;
        room.setExits(arr);
        when(charRepo.findById(1L))
                .thenReturn(Optional.of(charchar));
        when(gameRepo.findByClassName("Game")).thenReturn(registry);
        String json = mapper.writeValueAsString(registry);
        mockMvc.perform(MockMvcRequestBuilders.post("/inventory/pickup/1/Game")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(charRepo, times(1)).save(isA(GameCharacter.class));

    }

    @Test
    public void test_deleteInventory() throws Exception{
        GameCharacter charchar = new GameCharacter();
        Registry registry = new Registry();
        registry.setClassName("Game");
        charchar.setId(1L);
        charchar.setLocation(5);
        Room room  = new Room();
        room.setId(5L);
        Integer[] arr = new Integer[3];
        arr[0]=1;
        arr[1]=2;
        arr[2] = 3;
        room.setExits(arr);
        when(charRepo.findById(1L))
                .thenReturn(Optional.of(charchar));
        when(gameRepo.findByClassName("Game")).thenReturn(registry);
        String json = mapper.writeValueAsString(registry);
        mockMvc.perform(MockMvcRequestBuilders.post("/inventory/drop/1/Game")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(charRepo, times(1)).save(isA(GameCharacter.class));

    }

    @Test
    public void test_attack() throws Exception{
        GameCharacter charchar1 = new GameCharacter();
        GameCharacter charchar2 = new GameCharacter();
        charchar1.setId(1L);
        charchar1.setHitPoints(10);
        charchar2.setId(2L);
        charchar2.setHitPoints(15);
        when(charRepo.findById(1L))
                .thenReturn(Optional.of(charchar1));
        when(charRepo.findById(2L))
                .thenReturn(Optional.of(charchar2));
        String json = mapper.writeValueAsString(charchar1);
        mockMvc.perform(MockMvcRequestBuilders.post("/battle/1/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(charRepo, times(1)).save(isA(GameCharacter.class));

    }

}
