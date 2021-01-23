
package presentacion.utiles;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import configuracion.Configuracion;
import java.awt.Dimension;
import java.time.LocalTime;
import javax.swing.ImageIcon;

/**
 *
 * @author olmaton
 */
public class DatePickerUtil {
    private static final ImageIcon IMG_DATE_PICKER= new ImageIcon(DatePicker.class.getResource("/presentacion/imagenes/datepickerbutton1.png"));
    private static final ImageIcon IMG_TIME_PICKER= new ImageIcon(DatePicker.class.getResource("/presentacion/imagenes/timepickerbutton1.png"));
    private static final Dimension TIME_PICKER_BTN_DIMENSION = new Dimension(30, 30);
    
    public static void setFechaActual(DatePicker datePicker){
        datePicker.getComponentToggleCalendarButton().setText("");
        datePicker.getComponentToggleCalendarButton().setIcon(IMG_DATE_PICKER);
        datePicker.getSettings().setFormatForDatesCommonEra(Configuracion.FORMATO_FECHA_HORA);
        datePicker.setDateToToday();
    }
    
    public static void setFechaHoraStyle(DateTimePicker dateTimePicker){
        dateTimePicker.getDatePicker().getComponentToggleCalendarButton().setText("");
        dateTimePicker.getDatePicker().getComponentToggleCalendarButton().setIcon(IMG_DATE_PICKER);
        dateTimePicker.getDatePicker().getSettings().setFormatForDatesCommonEra(Configuracion.FORMATO_FECHA);
        dateTimePicker.getTimePicker().getComponentToggleTimeMenuButton().setText("");
        dateTimePicker.getTimePicker().getComponentToggleTimeMenuButton().setIcon(IMG_TIME_PICKER);
        dateTimePicker.getTimePicker().getComponentToggleTimeMenuButton().setPreferredSize(TIME_PICKER_BTN_DIMENSION);
        dateTimePicker.getTimePicker().getSettings().setFormatForDisplayTime(Configuracion.FORMATO_HORA);
    }
    
    public static void setFechaHoraMediaNoche(DateTimePicker dateTimePicker){
        dateTimePicker.getDatePicker().getComponentToggleCalendarButton().setText("");
        dateTimePicker.getDatePicker().getComponentToggleCalendarButton().setIcon(IMG_DATE_PICKER);
        dateTimePicker.getDatePicker().getSettings().setFormatForDatesCommonEra(Configuracion.FORMATO_FECHA);
        dateTimePicker.getDatePicker().setDateToToday();
        dateTimePicker.getTimePicker().getComponentToggleTimeMenuButton().setText("");
        dateTimePicker.getTimePicker().getComponentToggleTimeMenuButton().setIcon(IMG_TIME_PICKER);
        dateTimePicker.getTimePicker().getComponentToggleTimeMenuButton().setPreferredSize(TIME_PICKER_BTN_DIMENSION);
        dateTimePicker.getTimePicker().getSettings().setFormatForDisplayTime(Configuracion.FORMATO_HORA);
        dateTimePicker.getTimePicker().setTime(LocalTime.MIDNIGHT);
    }
    
    public static void setHoraActual(TimePicker timePicker){     
        timePicker.getComponentToggleTimeMenuButton().setText("");
        timePicker.getComponentToggleTimeMenuButton().setIcon(IMG_TIME_PICKER);
        timePicker.getComponentToggleTimeMenuButton().setPreferredSize(TIME_PICKER_BTN_DIMENSION);
        timePicker.getSettings().setFormatForDisplayTime(Configuracion.FORMATO_HORA);
        timePicker.setTimeToNow();
    } 
    
    public static void setHoraMediaNoche(TimePicker timePicker){     
        timePicker.getComponentToggleTimeMenuButton().setText("");
        timePicker.getComponentToggleTimeMenuButton().setIcon(IMG_TIME_PICKER);
        timePicker.getComponentToggleTimeMenuButton().setPreferredSize(TIME_PICKER_BTN_DIMENSION);
        timePicker.getSettings().setFormatForDisplayTime(Configuracion.FORMATO_HORA);
        timePicker.setTime(LocalTime.MIDNIGHT);
    } 
}
