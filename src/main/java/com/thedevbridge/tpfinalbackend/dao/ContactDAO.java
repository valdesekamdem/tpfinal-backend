package com.thedevbridge.tpfinalbackend.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.thedevbridge.tpfinalbackend.domain.Contact;

@Service
public class ContactDAO {
	
	private MongoClient mongoClient;
	private MongoCollection<Document> contactCollection;
	
	/**
	 * Connexion au serveur et à la base de données
	 * Récupération de la collection <b>contact</b>
	 */
	private void openServerConnection() {
		mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("phone");
		contactCollection = database.getCollection("contact");
	}
	
	/**
	 * Fermeture de la connexion au serveur de base de données
	 */
	private void closeServerConnection() {
		mongoClient.close();
	}
	
	/**
	 * Construire un document à partir d'un contact.
	 * @param contact
	 * @return
	 */
	private Document buildDocumentFromContact(Contact contact) {
		Document contactDocument = new Document("_id", contact.getId())
		.append("name", contact.getName())
		.append("phone", contact.getPhone())
		.append("email", contact.getEmail());
		return contactDocument;
	}
	
	/**
	 * Insertion d'un contact dans DB
	 * @param contact
	 */
	public void create(Contact contact) {
		openServerConnection();
		String id = UUID.randomUUID().toString();
		contact.setId(id);
		Document contactDocument = buildDocumentFromContact(contact);
		contactCollection.insertOne(contactDocument);
		closeServerConnection();
	}

	/**
	 * Retourne tout les contact contenus dans la BD
	 * @return
	 */
	public List<Contact> findAll() {
		openServerConnection();
		List<Contact> contacts = new ArrayList<Contact>();
		MongoCursor<Document> iterator = contactCollection.find().iterator();
		while (iterator.hasNext()) {
			Document document = (Document) iterator.next();
			Contact contact = buildContactFormDocument(document);
			contacts.add(contact);
		}
		closeServerConnection();
		return contacts;
	}

	/**
	 * Construire un contct à partir d'un document
	 * @param document
	 * @return
	 */
	private Contact buildContactFormDocument(Document document) {
		Contact contact = new Contact();
		contact.setId(document.getString("_id"));
		contact.setName(document.getString("name"));
		contact.setPhone(document.getString("phone"));
		contact.setEmail(document.getString("email"));
		return contact;
	}

}
