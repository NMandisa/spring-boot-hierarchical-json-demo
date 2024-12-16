package za.co.mkhungo.helper.base;

import java.util.List;
import java.util.Objects;

/**
 * @author Noxolo.Mkhungo
 */
public abstract class SubTreePopulator <T,R>{
    /**
     * Maps a single DTO to its corresponding subtree.
     * @param dto input DTO
     * @return transformed subtree
     */
    protected abstract R mapToSubTree(T dto);

    /**
     * Maps a list of DTOs to their corresponding subtrees.
     * @param dtos list of input DTOs
     * @return list of transformed subtrees
     */
    public List<R> mapToSubTrees(List<T> dtos) {
        if (Objects.isNull(dtos)) {
            return List.of();
        }
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(this::mapToSubTree)
                .toList();
    }
}
