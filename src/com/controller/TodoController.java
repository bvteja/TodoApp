package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Item;
import com.model.Todo;




@Controller
@RequestMapping("/todo")
public class TodoController {


	public static List<Todo> todos = new ArrayList<Todo>();

	@RequestMapping(value = "/createTodo/{title}", method = RequestMethod.GET)
	public @ResponseBody List<Todo> createTodo(@PathVariable String title) throws IOException, ParseException {

		String result = "";


		if(!title.equals("")){
			Todo todo = new Todo();
			todo.setCreatedOn(new Date());
			todo.setTitle(title);
			todo.setId(UUID.randomUUID().toString());
			todos.add(todo);

		}


		return todos;

	}

	@RequestMapping(value = "/createItem/{title}/{id}", method = RequestMethod.GET)
	public @ResponseBody Todo createTodo(@PathVariable String title , @PathVariable String id ) throws IOException, ParseException {

		

		Todo result = new Todo();
		if(!title.equals("")){
			for(Todo todo : todos){

				if(todo.getId().equals(id)){

					Item it = new Item();
					it.setIsCompleted(false);
					it.setTitle(title);
					it.setId(UUID.randomUUID().toString());
					todo.getItems().add(it);
					return  todo;

				}


			}

		}


		return null;

	}

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public @ResponseBody List<Todo> retrieveTodo() throws IOException, ParseException {
		return todos;

	}
	
	
	
	@RequestMapping(value = "/editTodo/{itemid}/{title}", method = RequestMethod.GET)
	public @ResponseBody List<Todo> editTodo( @PathVariable String itemid, @PathVariable String title) throws IOException, ParseException {
	
		
		for(Todo todo : todos){

			if(todo.getId().equals(itemid)){
				todo.setTitle(title);
				

			}
			
		}

		
		return todos;

	}
	
	@RequestMapping(value = "/editItem/{todoid}/{itemid}/{title}", method = RequestMethod.GET)
	public @ResponseBody Todo editItem(@PathVariable String todoid , @PathVariable String itemid, @PathVariable String title) throws IOException, ParseException {
	
		
		for(Todo todo : todos){

			if(todo.getId().equals(todoid)){
				for(Item item : todo.getItems()){

					if(item.getId().equals(itemid)){
						item.setTitle(title);
						return todo;
					}
					
				}
				

			}
			
		}

		
		return null;

	}
	
	@RequestMapping(value = "/removeItem/{todoid}/{itemid}", method = RequestMethod.GET)
	public @ResponseBody Todo removeItem(@PathVariable String todoid , @PathVariable String itemid) throws IOException, ParseException {
	
		int todoIndex = -1 ;
		int todoCounter = 0 ;
		
		int itemIndex = -1 ;
		int itemCounter= 0 ;
		for(Todo todo : todos){

			if(todo.getId().equals(todoid)){
				todoIndex = todoCounter ;

				for(Item item : todo.getItems()){

					if(item.getId().equals(itemid)){
						itemIndex = itemCounter ;
						break;
					}
					itemCounter ++ ;
				}
				

			}
			todoCounter ++ ;
		}

		if(todoIndex != -1 &&  itemIndex != -1){
			todos.get(todoIndex).getItems().remove(itemIndex);
		}
		return todos.get(todoIndex);

	}

	@RequestMapping(value = "/removeTodo/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Todo> removeTodo(@PathVariable String id) throws IOException, ParseException {
		int index = -1 ;
		int counter = 0;
		for(Todo todo : todos){

			if(todo.getId().equals(id)){
				index = counter;
			}

			counter ++ ;
		}

		if(index != -1){
			todos.remove(index);
		}
		return todos;

	}

	@RequestMapping(value = "/loadItems/{id}", method = RequestMethod.GET)
	public @ResponseBody Todo loadItems(@PathVariable String id) throws IOException, ParseException {

		int counter = 0;
		Todo myTodo = new Todo();
		for(Todo todo : todos){

			if(todo.getId().equals(id)){
				myTodo =  todo;
				break;
			}


		}


		return myTodo;

	}


}
