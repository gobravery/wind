package cp.csscis.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "tables")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tables extends ArrayList<Table> {
	@XmlElement
	public String primarycol;
	@XmlElement
	public String cpkey;
	@XmlElement(name = "table")
    public List<Table> getTables() {
        return this;
    }
}
