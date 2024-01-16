package Decorator;

public abstract class HeaderDecorator implements Header {
	private Header header;
	public HeaderDecorator(Header header) {
		this.header = header;
	}
	public Header getHeader() {
		return header;
	}

}
