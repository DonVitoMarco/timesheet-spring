package pl.thewalkingcode.service;

import pl.thewalkingcode.model.EntryQueryDTO;

import java.sql.Date;
import java.util.List;


public interface IEntriesQueryService {

    EntryQueryDTO getEntryByIndex(String username, int index);

    List<EntryQueryDTO> getAllEntries(String username, Date startDate, Date endDate);

}
