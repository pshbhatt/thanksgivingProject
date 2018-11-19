package com.learn.project.thanksgiving;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

	List<Item> games;

	@Before
	public void setup() {

		games = Arrays.asList(new Item(1, "Sword"),
				new Item(2, "Dagger"),
				new Item(3, "Smith&Weston"));
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void test_addGame() throws Exception {

		Item item =  new Item(1, "Sword");
		when(gameRepo.save(item))
				.thenReturn(item);

		String json = mapper.writeValueAsString(item);
		System.out.println(json);
		mockMvc.perform(MockMvcRequestBuilders.post("/object/create/Game")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());

		verify(gameRepo, times(1)).save(isA(Item.class));
		verifyNoMoreInteractions(gameRepo);
	}

    @Test
    public void test_deleteGame() throws Exception {

        Item item = new Item();
        item.setId(1);
        when(gameRepo.existsById(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/object/delete/Game/1"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(gameRepo, times(1)).existsById(1L);
        verify(gameRepo, times(1)).deleteById(1L);
        verifyNoMoreInteractions(gameRepo);

    }

}
