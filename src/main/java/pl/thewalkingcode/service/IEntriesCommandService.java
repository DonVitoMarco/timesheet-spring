package pl.thewalkingcode.service;


import pl.thewalkingcode.model.EntryFormDTO;

public interface IEntriesCommandService {

    void addNewEntry(EntryFormDTO entryFormDTO, String username);

    void editEntry(EntryFormDTO entryFormDTO, String username);

}
