package cp.csscis.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "columns")
public class Columns {
	private List<Column> columns;
	@XmlElements(value = { @XmlElement(name = "column", type = Column.class) })
    public List<Column> getColumns() {
        return columns;
    }
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
}
