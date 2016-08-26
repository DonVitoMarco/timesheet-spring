package pl.thewalkingcode.service;

import pl.thewalkingcode.model.EntryCommandDTO;
import pl.thewalkingcode.model.EntryDeleteFormDTO;
import pl.thewalkingcode.model.EntryFormDTO;


public interface IEntriesCommandService {

    boolean addNewEntry(EntryFormDTO entryFormDTO, String username);

    EntryCommandDTO editEntry(EntryFormDTO entryFormDTO, String username);

    int deleteEntry(EntryDeleteFormDTO entryDeleteFormDTO, String username);

    boolean approveEntry(String index);

    boolean notApproveEntry(String index);

}
