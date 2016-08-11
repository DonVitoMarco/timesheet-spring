package pl.thewalkingcode.service;

import pl.thewalkingcode.model.Entry;

import java.time.LocalDate;
import java.util.List;


public interface IEntryQueryService {

    List<Entry> getEntries(String username, LocalDate startDate, LocalDate endDate);

}
