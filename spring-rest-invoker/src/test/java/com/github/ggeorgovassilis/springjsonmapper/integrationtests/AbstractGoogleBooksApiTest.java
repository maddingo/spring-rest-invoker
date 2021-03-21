package com.github.ggeorgovassilis.springjsonmapper.integrationtests;

import com.github.ggeorgovassilis.springjsonmapper.services.BookService;
import com.github.ggeorgovassilis.springjsonmapper.services.Item;
import com.github.ggeorgovassilis.springjsonmapper.services.QueryResult;
import com.github.ggeorgovassilis.springjsonmapper.spring.SpringRestInvokerProxyFactoryBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Integration test with the google books API using the
 * {@link SpringRestInvokerProxyFactoryBean}
 * 
 * @author george georgovassilis
 */
@ExtendWith(SpringExtension.class)
public abstract class AbstractGoogleBooksApiTest {

	@Autowired
	protected BookService bookService;

	@Test
	public void testFindBooksByTitle() throws Exception {

		QueryResult result = bookService.findBooksByTitle("\"Philosophiae naturalis principia mathematica\"");
		assertThat(result,
			hasProperty("items",
				hasItem(hasProperty("volumeInfo",
					both(
						hasProperty("title", equalTo("Philosophiae naturalis principia mathematica")))
						.and(
							hasProperty("authors", hasItem("Isaac Newton"))
						)
					)
				)
			)
		);
	}

	@Test
	public void testFindBooksById() {
		Item item = bookService.findBookById("3h9_GY8v-hgC");
		assertThat(item, hasProperty("volumeInfo",
			both(
				hasProperty("title", equalTo("Philosophiae naturalis principia mathematica")))
				.and(
					hasProperty("authors", hasItem("Isaac Newton"))
				)
			)
		);
	}

}
