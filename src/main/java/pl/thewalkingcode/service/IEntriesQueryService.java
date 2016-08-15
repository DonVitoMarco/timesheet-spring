package pl.thewalkingcode.service;


import pl.thewalkingcode.model.EntryQueryDTO;

import java.util.List;

public interface IEntriesQueryService {

    EntryQueryDTO getEntryByIndex(int index);

    List<EntryQueryDTO> getAllEntries(String username);

}
