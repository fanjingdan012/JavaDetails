package pdf.model.m2;

import java.util.Date;

public class RecordFieldValueBO {
//  private RecordTypeField field = null;

  /**
   * The position inside a list type. Also for non-list types the position must be provided and
   * shows value of 1
   */
  private int position = 1;

  /**
   * Decimal value
   */
  private Double decimalValue = null;

  /**
   * Integer value
   */
  private Long integerValue = null;

  /**
   * String value
   */
  private String stringValue = null;

  /**
   * Date value
   */
  private Date dateValue = null;

  /**
   * Boolean value
   */
  private Boolean boolValue = null;

  /**
   * Reference link
   */
  private String refLink = null;

}
