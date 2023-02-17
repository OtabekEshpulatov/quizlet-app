package com.nooglers.dao;

import com.nooglers.domains.Folder;

import com.nooglers.enums.FolderStatus;
import jakarta.persistence.EntityTransaction;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public class FolderDao extends BaseDao<Folder, Integer> {
    @Override
    public Folder save(Folder folder) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(folder);
        transaction.commit();
        return folder;
    }

    @Override
    public Folder update(Folder folder) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Folder edittingFolder = entityManager.find(Folder.class, folder.getId());

        if (folder.getTitle() != null) edittingFolder.setTitle(folder.getTitle());
        if (folder.getDescription() != null) edittingFolder.setDescription(folder.getDescription());

        edittingFolder.setUpdatedAt(LocalDateTime.now());
        transaction.commit();
        return edittingFolder;
    }

    @Override
    public Folder delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Folder folder = entityManager.find(Folder.class, id);
        folder.setStatus(FolderStatus.DELETED);
        transaction.commit();
        return folder;
    }

    @Override
    public List<Folder> getAll() {
        return entityManager.createQuery("select f from Folder f", Folder.class).getResultList();
    }

    @Override
    public Folder get(@NonNull Integer id) {
        return entityManager.find(Folder.class, id);
    }

    public List<Folder> getFoldersById(Integer id) {
        return (List<Folder>) entityManager.createQuery("select * from folder where id= " + id).getResultList();
    }
}
