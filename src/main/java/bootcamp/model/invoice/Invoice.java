package bootcamp.model.invoice;

import java.util.List;
import bootcamp.model.products.Product;

public class Invoice {

	private int invoiceId;
	private Product product;
	private int count;

	public Invoice() {}

	public Invoice(int invoiceId, Product product, Integer count) {
		this.invoiceId = invoiceId;
		this.product = product;
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	}
