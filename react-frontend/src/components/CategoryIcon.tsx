import TaskIcon from '@mui/icons-material/Task';
import DryCleaningIcon from '@mui/icons-material/DryCleaning';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';
type CategoryIconProps = {
    id: number
}
export default function CategoryIcon(props: CategoryIconProps) {
    switch (props.id) {
        case 1:
            return <TaskIcon titleAccess={"task"} aria-label="task"/>
        case 3:
            return <DryCleaningIcon titleAccess={"chore"} aria-label="chore"/>
        default:
            return <AutoStoriesIcon titleAccess={"story"} aria-label="story"/>
    }
}