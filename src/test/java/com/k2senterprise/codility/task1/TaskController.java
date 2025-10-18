package com.k2senterprise.codility.task1;

@RestController
@RequestMapping("tasks")
public class TaskController {
    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TaskDto> findAll() {
        return Streams.stream(repository.findAll())
                .map(Task::toDto)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public TaskDto findById(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);

        if (task.isPresent()) {
            return task.get().toDto();
        }

        throw new TaskNotFound(id);
    }

    @PostMapping
    public Long create(@RequestBody TaskDto dto) {
        Task task = new Task(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.changeStatusTo(statusOf(dto).get());

        try {
            return repository.save(task).getId();
        } catch (DataIntegrityViolationException exception) {
            throw new TaskAlreadyExists(dto.getTitle());
        }
    }

    @PutMapping("/{id}")
    public void modify(@RequestBody TaskDto dto, @PathVariable Long id) {
        Optional<Task> found = repository.findById(id);

        if (found.isPresent()) {
            Task task = found.get();
            Optional<TaskStatus> taskStatus = statusOf(dto);

            taskStatus.ifPresent(task::changeStatusTo);

            if (dto.getDescription() != null) {
                task.setDescription(dto.getDescription());
            }

            if (dto.getTitle() != null) {
                task.changeTitle(dto.getTitle());
            }

            repository.save(task);
        } else {
            throw new TaskNotFound(id);
        }
    }

    private Optional<TaskStatus> statusOf(TaskDto dto) {
        return Optional.of(TaskStatus.valueOf(dto.getStatus()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);

        if (task.isPresent()) {
            repository.delete(task.get());
        } else {
            throw new TaskNotFound(id);
        }
    }
}

