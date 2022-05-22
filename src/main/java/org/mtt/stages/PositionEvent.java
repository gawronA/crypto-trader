package org.mtt.stages;

import org.mtt.dto.MovingAverageDto;

public interface PositionEvent {
    void invoke(MovingAverageDto dto);
}
