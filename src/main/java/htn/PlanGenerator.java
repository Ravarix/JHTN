package htn;

import htn.tasks.ComplexTask;
import htn.tasks.PrimitiveTask;
import htn.tasks.Task;
import utility.ICloneable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Doug on 10/8/2017.
 */
public class PlanGenerator<T extends ICloneable<T>> {

    private Domain<T> domain;

    public PlanGenerator(Domain<T> domain){
        this.domain = domain;
    }

    public List<PrimitiveTask<T>> findPlan(T worldState){
        PlannerState ps = new PlannerState(worldState);
        LinkedList<PlannerState> plannerStack = new LinkedList<>();
        ps.tasksToProcess.push(domain.rootTask);

        while(!ps.tasksToProcess.isEmpty()){
            Task<T> currentTask = ps.tasksToProcess.pop();
            if(currentTask instanceof ComplexTask){
                ComplexTask<T> complexTask = (ComplexTask<T>)currentTask;
                Method<T> method = findSatisfiedMethod(complexTask, ps);
                if(method != null){
                    //RecordDecompositionOfTask
                    plannerStack.push(new PlannerState(ps, complexTask));
                    for (int i = method.subtasks.size() - 1; i >= 0 ; --i)
                        ps.tasksToProcess.push(method.subtasks.get(i)); //add to front in order

                    ps.methodIdx = 0;
                }else{
                    //RestoreToLastDecomposedTask
                    ps = plannerStack.pop();
                    ps.tasksToProcess.push(ps.complexTask);
                    ps.methodIdx++;
                }
            }else{//PrimitiveTask
                PrimitiveTask<T> primitiveTask = (PrimitiveTask<T>)currentTask;
                if(primitiveTask.evaluate(ps.worldState)){ //caches Args
                    primitiveTask.apply(ps.worldState); //apply effects
                    ps.finalPlan.addLast(primitiveTask);
                }else{
                    //RestoreToLastDecomposedTask
                    ps = plannerStack.pop();
                    ps.tasksToProcess.push(ps.complexTask);
                    ps.methodIdx++;
                }
            }
        }

        return ps.finalPlan;
    }

    private Method<T> findSatisfiedMethod(ComplexTask<T> complexTask, PlannerState ps){
        while(ps.methodIdx < complexTask.methods.size()){
            Method<T> method = complexTask.methods.get(ps.methodIdx);
            if(method.condition.test(ps.worldState))
                return method;
            else
                ps.methodIdx++;
        }

        return null;
    }

    protected class PlannerState {
        public LinkedList<PrimitiveTask<T>> finalPlan;
        public LinkedList<Task<T>> tasksToProcess;
        public ComplexTask<T> complexTask;
        public int methodIdx;
        public T worldState;

        public PlannerState(T worldState){
            this.worldState = worldState.clone();
            this.finalPlan = new LinkedList<>();
            this.tasksToProcess = new LinkedList<>();
            this.complexTask = null;
            this.methodIdx = 0;
        }

        public PlannerState(PlannerState ps, ComplexTask<T> decompTask){
            this.finalPlan = new LinkedList<>(ps.finalPlan);
            this.tasksToProcess = new LinkedList<>(ps.tasksToProcess);
            this.complexTask = decompTask;
            this.worldState = ps.worldState.clone();
            this.methodIdx = ps.methodIdx;
        }
    }
}
