package mk.finki.ukim.seminarska.everythingyouneed.bootstrap;

import lombok.Getter;
import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.model.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<Company> companies = new ArrayList<>();
}
