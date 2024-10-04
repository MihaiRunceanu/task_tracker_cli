# task_tracker_cli
A tracker that marks tasks as TODO, IN-PROGRESS or DONE.

Sample solution for the task-tracker challenge from roadmap.sh.

This solution is made in Java with the ItelliJ Build System.

https://github.com/MihaiRunceanu/task_tracker_cli


Usage for this app:

  add (task description)            -> Add a task
  
  update (id) (New description)     -> Update the description of a task
  
  delete (id)                       -> Delete a task
  
  mark-in-progress (id)             -> Mark a task as in-progress
  
  mark-done (id)                    -> Mark a task as done
  
  list                              -> List all the tasks
  
  list done                         -> List all the tasks that are done
  
  list todo                         -> List all the tasks that are todo
  
  list in-progress                  -> List all the tasks that are in-progress
  
  stop/quit/exit                    -> Exit the application


Note:
Also you may need to change the filename(the path of the tasks file) in the TaskTrackerCLI.java file to your path for the file.
